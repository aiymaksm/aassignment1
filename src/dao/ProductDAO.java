package dao;

import database.DatabaseConnection;
import model.Product;
import model.FreshProduct;
import model.PackagedProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY product_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
                if (product != null) {
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public Product getProductById(int productId) {

        String sql = "SELECT * FROM product WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createProductFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean addFreshProduct(FreshProduct product) {

        String sql = """
                INSERT INTO product
                (name, price, category, stock, shelf_life_days, brand)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getShelfLifeDays());
            statement.setNull(6, Types.VARCHAR);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addPackagedProduct(PackagedProduct product) {

        String sql = """
                INSERT INTO product
                (name, price, category, stock, shelf_life_days, brand)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getStock());
            statement.setNull(5, Types.INTEGER);
            statement.setString(6, product.getBrand());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateProduct(Product product) {

        String sql = """
                UPDATE product
                SET name = ?,
                    price = ?,
                    category = ?,
                    stock = ?,
                    shelf_life_days = ?,
                    brand = ?
                WHERE product_id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getCategory());
            statement.setInt(4, product.getStock());

            if (product instanceof FreshProduct fresh) {
                statement.setInt(5, fresh.getShelfLifeDays());
                statement.setNull(6, Types.VARCHAR);
            } else if (product instanceof PackagedProduct packaged) {
                statement.setNull(5, Types.INTEGER);
                statement.setString(6, packaged.getBrand());
            } else {
                statement.setNull(5, Types.INTEGER);
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setInt(7, product.getProductId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteProduct(int productId) {

        String sql = "DELETE FROM product WHERE product_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Product> searchByName(String name) {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = createProductFromResultSet(resultSet);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public List<Product> searchByCategory(String category) {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE category = ? ORDER BY name";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = createProductFromResultSet(resultSet);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = createProductFromResultSet(resultSet);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public List<Product> getLowStockProducts(int threshold) {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE stock < ? ORDER BY stock ASC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, threshold);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = createProductFromResultSet(resultSet);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public List<FreshProduct> getFreshProducts() {

        List<FreshProduct> freshProducts = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE shelf_life_days IS NOT NULL ORDER BY product_id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                freshProducts.add(
                        new FreshProduct(
                                resultSet.getInt("product_id"),
                                resultSet.getString("name"),
                                resultSet.getDouble("price"),
                                resultSet.getString("category"),
                                resultSet.getInt("stock"),
                                resultSet.getInt("shelf_life_days")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return freshProducts;
    }


    private Product createProductFromResultSet(ResultSet rs) throws SQLException {

        int id = rs.getInt("product_id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        String category = rs.getString("category");
        int stock = rs.getInt("stock");

        Integer shelfLife = rs.getObject("shelf_life_days", Integer.class);
        String brand = rs.getString("brand");

        if (shelfLife != null) {
            return new FreshProduct(id, name, price, category, stock, shelfLife);
        }

        if (brand != null) {
            return new PackagedProduct(id, name, price, category, stock, brand);
        }

        return null;
    }
}
