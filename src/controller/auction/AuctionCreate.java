package controller.auction;

import entity.Auction;
import entity.User;
import provider.AuthProvider;
import repository.AuctionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuctionCreate", urlPatterns = {"/auction/create"})
public class AuctionCreate extends HttpServlet {
    protected AuctionRepository auctionRepository = new AuctionRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logged = AuthProvider.getAuthenticatedUser(request);
        if (logged == null) {
            response.sendRedirect("/auctions");
            return;
        }

        try {
            Auction auction = new Auction();
            auction.setTitle(request.getParameter("title"));
            auction.setPrice(Float.parseFloat(request.getParameter("price")));
            auction.setProducts(request.getParameter("products"));
            auction.setPublishAt(request.getParameter("publish_at"));
            auction.setUserId(logged.getId());

            if (auction.getTitle().isEmpty() || auction.getPrice() < 0) {
                throw new Exception("Auction validation");
            }

            this.auctionRepository.store(auction);
        } catch (Exception e) {
            response.sendRedirect("/auction/create");
            return;
        }

        response.sendRedirect("/auctions");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logged = AuthProvider.getAuthenticatedUser(request);
        if (logged == null) {
            response.sendRedirect("/auctions");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/resource/views/auction/create.jsp");

        dispatcher.forward(request, response);
    }
}
