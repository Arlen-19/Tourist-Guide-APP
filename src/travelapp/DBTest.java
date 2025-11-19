package travelapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTest {

    // ----------------- Database connection details -----------------
    static String url = "jdbc:mysql://localhost:3306/Tourist";
    static String user = "root";
    static String password = "1234";

    // ----------------- Model Classes -----------------
    public static class Tourist {
        int id;
        String name;
        int age;
        Date dob;
        String role;
        String email;
        String phone;

        public Tourist(int id, String name, int age, Date dob, String role, String email, String phone) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.dob = dob;
            this.role = role;
            this.email = email;
            this.phone = phone;
        }

        // Optional getters (helpful for external access)
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public int getId() { return id; }
    }

    public static class Attraction {
        int id;
        String name;
        String category;
        String location;
        int visitCount;
        String description;
        String image_reference;

        public Attraction(int id, String name, String category, String location, int visitCount, String description, String image_reference) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.location = location;
            this.visitCount = visitCount;
            this.description = description;
            this.image_reference = image_reference;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getLocation() { return location; }
        public int getVisitCount() { return visitCount; }
        public String getDescription() {return description; }
        public String getImageReference() {return image_reference; }
    }

    public static class Visit {
        int id;
        String touristName;
        String attractionName;
        Date visitDate;

        public Visit(int id, String touristName, String attractionName, Date visitDate) {
            this.id = id;
            this.touristName = touristName;
            this.attractionName = attractionName;
            this.visitDate = visitDate;
        }

        public String getTouristName() { return touristName; }
        public String getAttractionName() { return attractionName; }
        public Date getVisitDate() { return visitDate; }
    }

    public static boolean addAttraction(String name, String category, String location, String description, String imageReference) {
        String query = "INSERT INTO attractions (name, category, location, description, image_reference) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setString(3, location);
            stmt.setString(4, description);
            stmt.setString(5, imageReference);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ----------------- Get Connection -----------------
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    // ----------------- Get All Tourists -----------------
    public static List<Tourist> getTourists() {
        List<Tourist> list = new ArrayList<>();
        String query = "SELECT * FROM tourist";

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Tourist(
                        rs.getInt("tourist_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDate("dob"),
                        rs.getString("role"),
                        rs.getString("email"),
                        rs.getString("phone_no")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ----------------- Get All Attractions -----------------
    public static List<Attraction> getAttractions() {
    return getAttractions(0); // default call
}
    public static List<Attraction> getAttractions(int id) {
        List<Attraction> list = new ArrayList<>();

        boolean fetchAll = (id == 0);
        String query;

        if (fetchAll) {
    query = """
        SELECT a.attraction_id, a.name, a.category, a.location, a.description, a.image_reference,
               COUNT(v.visit_id) AS visit_count
        FROM attractions a
        LEFT JOIN visits v ON a.attraction_id = v.attraction_id
        GROUP BY a.attraction_id;
    """;
} else {
    query = """
        SELECT a.attraction_id, a.name, a.category, a.location, a.description, a.image_reference,
               COUNT(v.visit_id) AS visit_count
        FROM attractions a
        LEFT JOIN visits v ON a.attraction_id = v.attraction_id
        WHERE a.attraction_id = """ + id + """
        GROUP BY a.attraction_id;
    """;
}


        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Attraction(
                        rs.getInt("attraction_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("location"),
                        rs.getInt("visit_count"),
                        rs.getString("description"),
                        rs.getString("image_reference")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ----------------- Get All Visits -----------------
    public static List<Visit> getVisits() {
        List<Visit> list = new ArrayList<>();
        String query = """
            SELECT v.visit_id, t.name AS tourist_name, a.name AS attraction_name, v.visit_date
            FROM visits v
            JOIN tourist t ON v.tourist_id = t.tourist_id
            JOIN attractions a ON v.attraction_id = a.attraction_id;
        """;

        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Visit(
                        rs.getInt("visit_id"),
                        rs.getString("tourist_name"),
                        rs.getString("attraction_name"),
                        rs.getDate("visit_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ----------------- Test Method -----------------
    public static void main(String[] args) {
        List<Attraction> attractions = getAttractions();
        for (Attraction a : attractions) {
            System.out.println("Name: " + a.getName() + ", Visits: " + a.getVisitCount());
        }

        List<Tourist> tourists = getTourists();
        for (Tourist t : tourists) {
            System.out.println("Tourist: " + t.getName() + " (" + t.getEmail() + ")");
        }
    }
}
