<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Auction</title>

    <!-- Scripts -->
    <%@ include file = "/resource/views/parts/scripts.jsp" %>

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

    <!-- Styles -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

</head>
<body>
<div id="app">
    <%@ include file = "/resource/views/parts/nav_bar.jsp" %>
    <main class="py-4">
        <div class="container">
            <div class="card">
                <div class="card-header">
                    Create new auction
                </div>
                <div class="card-body">
                    <form action="/auction/create" method="POST">
                        <div class="form-group">
                            <label class="control-label">Title*</label>
                            <input class="form-control" name="title" required>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Start price*</label>
                            <input class="form-control" name="price" required type="number" min="0">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Products</label>
                            <input class="form-control" name="products">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Publish at</label>
                            <input class="form-control" name="publish_at" type="datetime-local">
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label">Images</label>--%>
                            <%--<input class="form-control" name="images" multiple type="file">--%>
                        <%--</div>--%>
                        <button class="btn btn-success">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
