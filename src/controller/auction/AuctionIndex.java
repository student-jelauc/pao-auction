package controller.auction;

import entity.Auction;
import repository.AuctionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AuctionIndex", urlPatterns = {"/auctions"})
public class AuctionIndex extends HttpServlet {
    protected AuctionRepository auctionRepository = new AuctionRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Auction> auctions = new ArrayList<Auction>();

        try {
            auctions = this.auctionRepository.getAll();
        } catch (Exception e) {

        }

        request.setAttribute("auctions", auctions);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/resource/views/auction/index.jsp");

        dispatcher.forward(request, response);
    }
}
