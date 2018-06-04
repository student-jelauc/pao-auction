<%@ page import="entity.Auction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="repository.BidRepository" %>
<%@ page import="entity.Bid" %>
<% ArrayList<Auction> auctions = (ArrayList<Auction>)request.getAttribute("auctions"); %>
<% BidRepository bidRepository = new BidRepository(); %>

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
                    Auctions
                </div>
                <div class="card-body">
                    <% if (logged != null) { %>
                        <a href="/auction/create" class="btn btn-success mb-3">Create new auction</a>
                    <% } %>
                    <div class="list-group">
                        <% for (Auction auction : auctions) {%>
                            <div class="list-group-item">
                                <a href="/auction?id=<%= auction.getId() %>" >
                                    <%= auction.getTitle() %>
                                </a>
                                <% Bid activeBid = bidRepository.getAuctionCurrentBid(auction.getId()); %>
                                <% if (activeBid != null) { %>
                                    <span class="float-right text-success">
                                        <%= activeBid.getPrice() %> lei
                                    </span>
                                <% } else { %>
                                    <span class="float-right">
                                        <%= auction.getPrice() %> lei
                                    </span>
                                <% } %>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
