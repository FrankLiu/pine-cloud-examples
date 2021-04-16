import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class Description to be replaced
 *
 * @author frank.liu
 * @version 1.0
 * @since 2021/4/16
 */
public class PrintDemo {
    public static void main(String[] args) {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, aset);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 200, 200, pservices,
                defaultService, flavor, aset);
        if (service != null) {
            try {
                DocPrintJob pj = service.createPrintJob();
                FileInputStream fis = new FileInputStream("README.md");// 打印D盘HELLO.txt文档。
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(fis, flavor, das);
                pj.print(doc, aset);
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();
            } catch (PrintException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("打印失败");
        }
    }
}
