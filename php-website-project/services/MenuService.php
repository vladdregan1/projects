<?php

namespace app\services;

use app\models\Meniu;
use app\models\Product;
use app\models\ProductSearch;
use app\models\ProductType;
use app\models\ProductTypeSearch;

class MenuService
{
    public function getMenuItemsPerTypeAndToday(): array
    {
        $today = date('Y-m-d');

        // @todo: are * necessary
        $query = Meniu::find()
            ->select(['meniu.*', 'product.*', 'product_type.*'])
            ->innerJoin('product', 'meniu.id_product = product.id_product')
            ->innerJoin('product_type', 'product.id_product_type = product_type.id_product_type')
            ->where(['meniu.day' => $today]);
        $data = $query->asArray()
            ->all();

        $productTypes = [];
        foreach ($data as $row) {
            $productTypes[$row['type']][] = $row;
        }

        return $productTypes;

    }

}
