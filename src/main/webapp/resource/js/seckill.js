//存放主要js交互逻辑
//javascript模块化

function p(msg) {
    console.log(msg);
}

var seckill = {
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
            return "/seckills/time/now";
        },
        expose: function (seckillId) {
            return "/seckills/"+seckillId+"/expose";
        },
        execution: function (seckillId,md5) {
            return "/seckills/"+seckillId+"/"+md5+"/execution";
        }
    },
    utils: {
        //验证手机号
        validatePhone: function (phone) {
            var phoneRegex = /^1[3|4|5|8][0-9]\d{4,8}$/;
            return (phone && phoneRegex.test(phone));
        }
    },
    //执行秒杀
    handleSeckill:function (seckillId,node) {
        //隐藏node结点并添 ‘执行秒杀’按钮
        node.hide()
            .html("<a class='btn btn-primary' id='handle-seckill'>执行秒杀</a>");

        //向服务器请求秒杀地址url
        $.post(seckill.URL.expose(seckillId),{},function (res) {
            if(res && res.success) {
                var exposer = res.data;
                //如果已经开始秒杀
                //获取秒杀地址
                //获取md5，构建秒杀url
                var md5 = exposer.md5;
                var seckillUrl = seckill.URL.execution(seckillId,md5);
                if(exposer.exposed) {
                    //绑定一次秒杀事件
                    $("#handle-seckill").one("click",function () {
                         $.post(seckillUrl,{},function (res) {
                             p(res);
                             if(res && res.success) {
                                 var execution = res.data;
                                 p(execution)
                                 var state = execution.state;
                                 var stateInfo = execution.stateInfo;
                                 //显示秒杀结果
                                 node.html("<span class='label label-success'>"+stateInfo+"</span>");
                             } else {
                                 p("result : "+res);
                                 node.html("<span class='label label-danger'>"+res.error+"</span>");
                             }
                         });
                    });

                    node.show();
                } else {
                    //如果还没开启秒杀，则重新开始计时
                    var start = exposer.start;
                    var end = exposer.end;
                    var now = exposer.now;

                    seckill.countDown(seckillId,start,end,now);
                }
            } else {
                p("result : "+res);
            }
        });

    },
    //开始倒计时或显示秒杀
    countDown: function (seckillId,startTime,endTime,nowTime) {
        //获取秒杀信息显示Node
        var seckillBox = $("#seckill-box");

        //判断系统时间是不是大于结束时间，如果是，则显示秒杀结束
        if(nowTime > endTime) {
            seckillBox.html("秒杀结束");

            //如果系统时间小于秒杀开始时间，则显示倒计时
        } else if(nowTime < startTime) {
            //秒杀时间，加上1000是防止浏览器与系统时间的延迟
            var seckillTime = new Date(startTime+1000);
            seckillBox.countdown(seckillTime,function (event) {
                var format = event.strftime("秒杀倒计时：%D天 %H时 %M分 %S秒");
                seckillBox.html(format);
                //监听倒时计结束事件，当倒计时结束时，显示秒杀按钮
            }).on("finish.countdown",function () {
                //倒计时完成，显示秒杀按钮
                seckill.handleSeckill(seckillId,seckillBox);
            });
        } else {
            //否则显示秒杀按钮供用户秒杀
            seckill.handleSeckill(seckillId,seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (param) {
            //手机验证和登陆，计时交互，规划我们的交互流程

            //从cookie中获取用户的手机号
            var killPhone = $.cookie("killPhone");
            //如果cookie中没有手机号，则要求用户先输入手机号
            if(!killPhone) {
                //显示modal让用户输入手机号
                var phoneModal = $("#killPhoneModal");
                //显示modal
                phoneModal.modal({
                    show: true,//让modal显示
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//键盘上的 esc 键被按下时是否关闭模态框。
                });
                //监听按钮提交事件
                $("#killPhoneBtn").click(function () {
                    //获得用户输入的phone
                    var phoneVal = $("#killPhone").val();
                    //验证输入的手机号是否正确
                    if(!seckill.utils.validatePhone(phoneVal)) {
                        //如果手机号不正确，则显示错误信息
                        $("#killPhoneMsg").hide().html('<label class="label label-danger">手机号输入错误</label>').show(300);
                    } else {
                        //如果用户输入的手机号正确，则将正确的手机号写入cookie
                        $.cookie("killPhone",phoneVal,{expires:7,path:"/seckills"});
                        //刷新页面
                        window.location.reload();
                    }
                });
            } else {
                //用户已经登陆，开始显示倒计时或显示秒杀按钮

                //从服务器获取当前时间，并根据服务器时间倒计时
                $.get(seckill.URL.now(),{},function (res) {
                    //如果从服务器获取时间成功，则开始计时或秒杀
                    if(res && res.success) {
                        //获取从页面获取的值
                        var seckillId = param['seckillId'];
                        var startTime = param['startTime'];
                        var endTime = param['endTime'];

                        var nowTime = res["data"];

                        seckill.countDown(seckillId,startTime,endTime,nowTime);
                    } else {
                        p("result + "+res);
                    }
                });

            }
        }
    }
};