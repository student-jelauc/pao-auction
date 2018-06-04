<%@ page import="entity.User" %>
<%@ page import="provider.AuthProvider" %>
<% User logged = (User) AuthProvider.getAuthenticatedUser(request); %>
<nav class="navbar navbar-expand-md navbar-light">
    <div class="container">
        <a class="navbar-brand" href="/">
            Auction
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- Left Side Of Navbar -->
            <ul class="navbar-nav mr-auto">
                <li><a class="nav-link" href="/auctions">Active bids</a></li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <% if (logged == null) { %>
                    <!-- Right Side Of Navbar -->

                        <li><a class="nav-link" href="/login">Login</a></li>
                        <li><a class="nav-link" href="/register">Register</a></li>

                <% } else { %>
                    <li class="nav-item dropdown">
                        <a id="navbarDropdown" class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-pre>
                            <%= logged.getUsername() %> <span class="caret"></span>
                        </a>

                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/my/auctions">
                                My auctions
                            </a>
                            <a class="dropdown-item" href="/logout">
                                Logout
                            </a>
                        </div>
                    </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>