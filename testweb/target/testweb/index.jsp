<html>
<header>
    <script src="js/jquery-1.9.1.min.js"></script>
</header>
<body>
<h2>Hello World!</h2>
<input type="button" onclick="request()" value="submit" />
<script>
    function request() {
        $.ajax({
            url:"http://localhost:8080/system_threshold/save",
            data:{
                "businessid":111,
                "field":1,
                "callback":"onBack"
            },
            dataType:'jsonp',
            jsonpCallBack:"onBack",
            error:function (error) {
                alert(error);
            }
        });
    }

    function onBack(data) {
        debugger;
        alert(data);
    }
</script>
</body>
</html>
