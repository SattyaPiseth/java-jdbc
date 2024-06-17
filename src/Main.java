import controller.CustomerController;
import controller.OrderController;
import controller.ProductController;
import exception.OrderNotFoundException;
import model.dto.AddCustomerDto;
import model.dto.AddOrderDto;
import model.dto.ProductDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws OrderNotFoundException {
        CustomerController customerController = new CustomerController();
        ProductController productController = new ProductController();
        OrderController orderController = new OrderController();

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Get all customers");
            System.out.println("2. Get all products");
            System.out.println("3. Get all orders");
            System.out.println("4. Add new customer");
            System.out.println("5. Add new product");
            System.out.println("6. Add new order");
            System.out.println("7. Delete customer");
            System.out.println("8. Delete product");
            System.out.println("9. Delete order");
            System.out.println("10. Update customer");
            System.out.println("11. Update product");
            System.out.println("12. Update order");
            System.out.println("13. Search customer by name");
            System.out.println("14. Search customer by id");
            System.out.println("15. Search product by id");
            System.out.println("16. Search order by id");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(customerController.getAllCustomers());
                    break;
                case 2:
                    System.out.println(productController.getAllProducts());
                    break;
                case 3:
                    System.out.println(orderController.getAllOrders());
                    break;
                case 4:
                    System.out.print("[+] Insert customer name: ");
                    String name = scanner.next();
                    System.out.print("[+] Insert customer email: ");
                    String email = scanner.next();
                    System.out.print("[+] Insert customer password: ");
                    String password = scanner.next();
                    customerController.addNewCustomer(AddCustomerDto.builder()
                                    .name(name)
                                    .email(email)
                                    .password("{$%^&}"+password)
                            .build());
                    break;
                case 5:
                    System.out.print("[+] Insert product name: ");
                    String productName = scanner.next();
                    System.out.print("[+] Insert product description: ");
                    String productDescription = scanner.next();
                    System.out.print("[+] Insert product code: ");
                    String productCode = scanner.next();
                    System.out.print("[+] Insert product imported date: ");
                    String productImportedDate = scanner.next();
                    System.out.print("[+] Insert product expirated date: ");
                    String productExpiratedDate = scanner.next();
                    productController.addNewProduct(ProductDto.builder()
                                    .productName(productName)
                                    .productCode(productCode)
                                    .productDescription(productDescription)
                                    .imported_at(productImportedDate)
                                    .expired_at(productExpiratedDate)
                            .build());
                    break;
                case 6:
                    System.out.print("[+] Insert order name: ");
                    String orderName = scanner.next();
                    System.out.print("[+] Insert order description: ");
                    String orderDescription = scanner.next();
                    System.out.print("[+] Insert customer id: ");
                    int customerId = scanner.nextInt();
                    System.out.print("[+] Insert product id: [multiple, separated by comma]: [e.g. 1,2,3,4,] \t");
                    String productIds = scanner.next();

                    orderController.addNewOrder(AddOrderDto.builder()
                            .order_name(orderName)
                            .order_description(orderDescription)
                            .cus_id(customerId)
                            .order_at(LocalDate.now().toString())
                            .pro_id(Arrays.stream(productIds.split(",")).map(
                                    Integer::parseInt
                            ).toList())
                            .build());
                    break;
                case 7:
                    // Add code to delete a customer
                    System.out.print("[+] Insert customer id: ");
                    customerController.deleteCustomerById(scanner.nextInt());
                    break;
                case 8:
                    System.out.print("[+] Insert product id: ");
                    productController.deleteProductById(scanner.nextInt());
                    break;
                case 9:
                    System.out.print("[+] Insert order id: ");
                    orderController.deleteOrderById(scanner.nextInt());
                    break;
                case 10:
                    System.out.print("[+] Insert customer id: ");
                    customerController.updateCustomerById(scanner.nextInt());
                    break;
                case 11:
                    System.out.print("[+] Insert product id: ");
                    productController.updateProductById(scanner.nextInt());
                    break;
                case 12:
                    System.out.print("[+] Insert order id: ");
                    orderController.updateOrder(scanner.nextInt());
                    break;
                case 13:
                    System.out.print("[+] Insert customer name: ");
                    customerController.searchCustomerByName(scanner.next()).forEach(System.out::println);
                    break;
                case 14:
                    System.out.print("[+] Insert customer id: ");
                    System.out.println(customerController.getCustomerById(scanner.nextInt()));
                    break;
                case 15:
                    System.out.print("[+] Insert product id: ");
                    System.out.println(productController.getProductById(scanner.nextInt()));
                    break;
                case 16:
                    System.out.print("[+] Insert order id: ");
                    System.out.println(orderController.getOrderById(scanner.nextInt()));
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);
    }
}