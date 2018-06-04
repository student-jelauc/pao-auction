package controller.bid;

import entity.Auction;
import entity.Bid;
import entity.User;
import provider.AuthProvider;
import repository.AuctionRepository;
import repository.BidRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BidCreate", urlPatterns = {"/bid/create"})
public class BidCreate extends HttpServlet {
    protected BidRepository bidRepository = new BidRepository();
    protected AuctionRepository auctionRepository = new AuctionRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User logged = AuthProvider.getAuthenticatedUser(request);
        if (logged == null) {
            response.sendRedirect("/auctions");
            return;
        }

        Auction auction;
        try {
            auction = this.auctionRepository.getById(Integer.parseInt(request.getParameter("auction_id")));
        } catch (Exception e) {
            response.sendRedirect("/auctions");
            return;
        }

        Bid currentBid = null;
        try {
            currentBid = this.bidRepository.getAuctionCurrentBid(auction.getId());
        } catch (Exception e) {}

        try {
            float bidPrice = Float.parseFloat(request.getParameter("bid"));
            if (currentBid == null && bidPrice < auction.getPrice()) {
                throw new Exception("Bid validation");
            } else if (currentBid != null && bidPrice <= currentBid.getPrice()) {
                throw new Exception("Bid validation");
            }

            Bid bid = new Bid();
            bid.setAuctionId(auction.getId());
            bid.setUserId(logged.getId());
            bid.setPrice(bidPrice);

            this.bidRepository.store(bid);
        } catch (Exception e) { };

        response.sendRedirect("/auction?id=" + auction.getId());
    }
}
