package info.mushonga.search.iservice.func;

import info.mushonga.search.model.product.Product;
import info.mushonga.search.repository.product.ProductRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Utility class for product manipulation
 *
 *
 * @author percym
 */
public class ProductFunctions {

    @Autowired
    private ProductRepository productRepository;

    public Product addMetrics(Product product){
        product.setLastSearchedDate(LocalDateTime.now());
        product.setTotalSearchedTimes(product.getTotalSearchedTimes().add(BigDecimal.ONE));
        return product;

    }
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();

            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();

            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }

}
