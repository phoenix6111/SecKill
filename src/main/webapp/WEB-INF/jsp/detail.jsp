<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/tag.jsp"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>秒杀商品详情页</title>

    <jsp:include page="common/head.jsp"/>
    <style type="text/css">
        .container {
            margin-top: 20px;
        }
    </style>

</head>
<body>

<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h2>${seckill.name}</h2>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <!--展示图标-->
                <span class="glyphicon glyphicon-time"></span>
                <!--展示倒计时-->
                <span class="glyphicon" id="seckill-box"> </span>
            </h2>
        </div>
    </div>
</div>

<!--登陆弹出层，输入电话-->
<div class="modal fade" id="killPhoneModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone">秒杀电话</span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" id="killPhone" name="killPhone" placeholder="填手机号^o^"
                               class="form-control">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span class="glyphicon" id="killPhoneMsg"></span>
                <button type="button" class="btn btn-primary" id="killPhoneBtn">
                    <span class="glyphicon glyphicon-phone"></span>
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- jQuery Cookie 操作Cookie插件-->
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jquery CountDown 倒计时插件-->
<script src="http://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<script src="/resource/js/seckill.js"></script>
<script type="text/javascript">
    //使用EL表达式传入参数，初始化详情页交互逻辑
    seckill.detail.init({
        seckillId: ${seckill.seckillId},
        startTime: ${seckill.startTime.time},
        endTime: ${seckill.endTime.time}
    });
</script>
</body>
</html>
