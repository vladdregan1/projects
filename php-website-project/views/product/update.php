<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\Product $model */
/** @var $productTypeItems */

$this->title = 'Update Product: ' . $model->id_product;
$this->params['breadcrumbs'][] = ['label' => 'Products', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_product, 'url' => ['view', 'id_product' => $model->id_product]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="product-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
        'productTypeItems' => $productTypeItems,
    ]) ?>

</div>
