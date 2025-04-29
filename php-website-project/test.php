<?php
try {
    $pdo = new PDO("mysql:host=localhost;dbname=bistro", "vlad", "secret123");
    echo "Conexiune reușită!";
} catch (PDOException $e) {
    echo "Eroare: " . $e->getMessage();
}
?>
