package controller.account;

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
import java.util.ArrayList;

@WebServlet(name = "AccountAuctionShow", urlPatterns = {"/my/auctions"})
public class AccountAuctionShow extends HttpServlet {
    protected AuctionRepository auctionRepository = new AuctionRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logged = AuthProvider.getAuthenticatedUser(request);
        if (logged == null) {
            response.sendRedirect("/home");
            return;
        }

        ArrayList<Auction> userAuctions = new ArrayList<Auction>();
        try {
            userAuctions = this.auctionRepository.getUserAuctions(logged.getId());
        } catch (Exception e) {}

        request.setAttribute("auctions", userAuctions);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/resource/views/account/auctions.jsp");

        dispatcher.forward(request, response);
    }
}
