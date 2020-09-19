package by.realovka.servlets;

import by.realovka.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedHashMap<Product, String> basket = new LinkedHashMap<>();
        String [] quality = req.getParameterValues("qualityProduct");
        List<Product> products =(List<Product>) req.getSession().getAttribute("productsList");
        int i, j;
        for (i=0, j=0; i<products.size(); i++, j++){
                basket.put(products.get(i), quality[j]);
            }

        LinkedHashMap<Product,String> finalBasket =getResult(basket);
        req.getSession().setAttribute("basket", finalBasket);
        req.getRequestDispatcher("/basket.jsp").forward(req, resp);
    }

    private LinkedHashMap<Product, String> getResult(LinkedHashMap<Product, String> result){
         for (Iterator<Map.Entry<Product, String>> it=result.entrySet().iterator(); it.hasNext();){
             Map.Entry<Product,String> entry=it.next();
             if(entry.getValue().equals("")){
                 it.remove();
             }
         }
         return result;
    }
}
