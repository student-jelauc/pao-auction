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

@WebServlet(name = "AuctionShow", urlPatterns = {"/auction"})
public class AuctionShow extends HttpServlet {
    AuctionRepository auctionRepository = new AuctionRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        Auction auction;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            auction = this.auctionRepository.getById(id);
        } catch (Exception e) {
            response.sendRedirect("/auctions");
            return;
        }

        request.setAttribute("auction", auction);
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/resource/views/auction/show.jsp");

        dispatcher.forward(request, response);
    }
}
