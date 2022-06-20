INSERT INTO store_items (product_id, quantity)
SELECT product_id, CAST(REPLACE(name, 'Product') AS INT) AS quantity FROM store_products;