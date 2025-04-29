<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\ProductType $model */

$this->title = 'Update Product Type: ' . $model->id_product_type;
$this->params['breadcrumbs'][] = ['label' => 'Product Types', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_product_type, 'url' => ['view', 'id_product_type' => $model->id_product_type]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="product-type-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
