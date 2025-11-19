<h1>🧭 Tourist Guide App</h1>
<p>A Java + JavaFX desktop application that helps users explore famous tourist attractions across India.  
The app uses MySQL for data storage and provides a clean UI built with JavaFX.</p>

<hr>

<h2>📌 Features</h2>
<ul>
  <li>🏛️ Browse tourist attractions</li>
  <li>📝 View full details including description and image</li>
  <li>🔍 Search & explore attractions in detail</li>
  <li>➕ Add new attractions through the UI</li>
  <li>🎨 Modern JavaFX user interface</li>
  <li>🛢️ MySQL database connectivity</li>
</ul>

<hr>

<h2>📁 Project Structure</h2>
<pre>
Tourist-Guide-APP/
│── src/
│   ├── travelapp/
│   │   ├── DBTest.java
│   │   └── ui/
│   │       ├── ExplorePage.java
│   │       ├── AttractionDetailsPage.java
│   │       ├── AddAttractionPage.java
│   │       └── MainPage.java
│── assets/
│── lib/                     # You will create this manually
│── README.md
</pre>

<hr>

<h2>🛠️ Requirements</h2>
<ul>
  <li>✔ Java JDK 17 or newer</li>
  <li>✔ JavaFX SDK (to be downloaded)</li>
  <li>✔ MySQL Server + MySQL Workbench</li>
  <li>✔ MySQL Connector/J (JDBC Driver)</li>
</ul>

<hr>

<h2>📥 Step 1 — Download Required Libraries</h2>

<h3>📌 1️⃣ Download JavaFX SDK</h3>
<p>Download from: <a href="https://gluonhq.com/products/javafx/">https://gluonhq.com/products/javafx/</a></p>

<h3>📌 2️⃣ Download MySQL Connector/J (JDBC Driver)</h3>
<p>Download from: <a href="https://dev.mysql.com/downloads/connector/j/">https://dev.mysql.com/downloads/connector/j/</a></p>

<h3>📌 3️⃣ Create <code>lib</code> Folder</h3>
<p>Inside project root, create:</p>
<pre>
Tourist-Guide-APP/lib/
</pre>

<h3>📌 4️⃣ Add Downloads Inside <code>lib</code></h3>
<pre>
Tourist-Guide-APP/lib/javafx-sdk-25.0.1/
Tourist-Guide-APP/lib/mysql-connector-j-8.3.0.jar
</pre>

<hr>

<h2>🗄️ Step 2 — Database Setup (MySQL)</h2>

<h3>1️⃣ Create Database</h3>
<pre>
CREATE DATABASE travel_db;
USE travel_db;
</pre>

<h3>2️⃣ Create Table</h3>
<pre>
CREATE TABLE attractions (
    attraction_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(100),
    location VARCHAR(100),
    description TEXT,
    image_reference VARCHAR(255)
);
</pre>

<h3>3️⃣ Insert Example</h3>
<pre>
INSERT INTO attractions (name, category, location, description, image_reference)
VALUES ('Taj Mahal', 'Historical', 'Agra', 'A UNESCO world heritage monument...', 'assets/taj_mahal.jpg');
</pre>

<hr>

<h2>⚙️ Step 3 — Configure DB Credentials</h2>

<p>Open <code>DBTest.java</code> and update:</p>

<pre>
static String url = "jdbc:mysql://localhost:3306/travel_db";
static String user = "root";
static String password = "your_password";
</pre>

<hr>

<h2>💻 Step 4 — Compile the Project</h2>

<h3>1️⃣ Compile JavaFX + Your Source Code</h3>

<pre>
javac --module-path "lib/javafx-sdk-25.0.1/lib" --add-modules javafx.controls,javafx.fxml ^
-cp "lib/mysql-connector-j-8.3.0.jar" ^
-d out src/travelapp/DBTest.java src/travelapp/ui/*.java
</pre>

<p><strong>Notes:</strong></p>
<ul>
  <li><code>--module-path</code> → Points to JavaFX SDK folder</li>
  <li><code>-cp</code> → Adds MySQL connector to classpath</li>
  <li><code>-d out</code> → Output folder for compiled classes</li>
</ul>

<hr>

<h2>🚀 Step 5 — Run the Application</h2>

<pre>
java --module-path "lib/javafx-sdk-25.0.1/lib" --add-modules javafx.controls,javafx.fxml ^
-cp "out;lib/mysql-connector-j-8.3.0.jar" travelapp.ui.MainPage
</pre>

<p>✔ Make sure <strong>MainPage</strong> is the correct entry class.</p>

<hr>

<h2>▶️ Running in VS Code / IntelliJ</h2>

<h3>Add JavaFX libraries manually:</h3>
<pre>
lib/javafx-sdk-25.0.1/lib
</pre>

<h3>Add MySQL Connector:</h3>
<pre>
lib/mysql-connector-j-8.3.0.jar
</pre>

<h3>VM Options:</h3>
<pre>
--module-path "lib/javafx-sdk-25.0.1/lib" --add-modules javafx.controls,javafx.fxml
</pre>

<hr>

<h2>🚀 Future Improvements</h2>
<ul>
  <li>Google Maps integration</li>
  <li>Bookmark attractions</li>
  <li>AI-based recommendations</li>
  <li>User authentication</li>
</ul>

<hr>

<h2>👨‍💻 Author</h2>
<p><strong>Arlen Dmello</strong><br>
GitHub: <a href="https://github.com/Arlen-19">https://github.com/Arlen-19</a></p>
GitHub: <a href="https://github.com/dmelloarlen">https://github.com/dmelloarlen</a></p>
<p><strong>Hansy Rodrigues</strong><br>
<p><strong>Jess Tuscano</strong><br>
