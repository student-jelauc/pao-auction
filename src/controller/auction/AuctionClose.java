package controller.auction;

import entity.Auction;
import entity.User;
import provider.AuthProvider;
import repository.AuctionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AuctionClose", urlPatterns = {"/auction/close"})
public class AuctionClose extends HttpServlet {
    protected AuctionRepository auctionRepository = new AuctionRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        Auction auction;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            auction = this.auctionRepository.getById(id);
        } catch (Exception e) {
            response.sendRedirect("/auctions");
            return;
        }

        User logged = AuthProvider.getAuthenticatedUser(request);
        if (logged == null || auction.getUserId() != logged.getId()) {
            response.sendRedirect("/auction?id=" + auction.getId());
            return;
        }

        auction.setIsClosed(1);
        try {
            this.auctionRepository.update(auction);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        response.sendRedirect("/auction?id=" + auction.getId());
    }
}
