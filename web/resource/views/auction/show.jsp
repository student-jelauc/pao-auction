<%@ page import="entity.Auction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="repository.BidRepository" %>
<%@ page import="entity.Bid" %>
<%@ page import="repository.UserRepository" %>
<% Auction auction = (Auction) request.getAttribute("auction"); %>
<% BidRepository bidRepository = new BidRepository(); %>
<% UserRepository userRepository = new UserRepository(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

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
                    <%= auction.getTitle() %>
                </div>
                <div class="card-body">
                    <% if (logged != null && logged.getId() == auction.getUserId() && auction.getIsClosed() != 1) { %>
                        <form action="/auction/close?id=<%= auction.getId() %>" method="POST">
                            <button type="submit" class="btn btn-warning mb-3">Close auction</button>
                        </form>
                    <% } %>
                    <% if (auction.getIsClosed() == 1) { %>
                        <button class="btn btn-warning mb-3">Auction closed</button>
                    <% } %>
                    <div class="form-group">
                        <label class="control-label">Auction started by</label>
                        <input class="form-control" disabled value="<%= userRepository.getById(auction.getUserId()).getUsername() %>">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Title</label>
                        <input class="form-control" name="title" disabled value="<%= auction.getTitle() %>">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Start price</label>
                        <input class="form-control" name="price" disabled value="<%= auction.getPrice() %>">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Products</label>
                        <input class="form-control" name="products" disabled value="<%= auction.getProducts() %>">
                    </div>
                    <div class="form-group">
                        <label class="control-label">Publish at</label>
                        <input class="form-control" name="publish_at" disabled value="<%= auction.getPublishAt() %>">
                    </div>
                    <% Bid activeBid = bidRepository.getAuctionCurrentBid(auction.getId()); %>
                    <% if (activeBid != null) { %>
                        <div class="form-group">
                            <label class="control-label">Current bid</label>
                            <div class="form-inline">
                                <input class="form-control mr-2" disabled value="<%= userRepository.getById(activeBid.getUserId()).getUsername() %>">
                                <input class="form-control text-success" disabled value="<%= activeBid.getPrice() %>">
                            </div>
                        </div>
                    <% } %>
                    <% if (logged != null && auction.getIsClosed() != 1) { %>
                        <form action="/bid/create?auction_id=<%= auction.getId() %>" method="POST">
                            <div class="form-group">
                                <input placeholder="Your bid" name="bid" class="form-control" type="number" min="<% if (activeBid != null) { %><%= (activeBid.getPrice() + 1) %><% } else { %><%= auction.getPrice() %><% } %>">
                            </div>
                            <button class="btn btn-success">Place a bid</button>
                        </form>
                    <% } %>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
