<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\OrderAddress $model */

$this->title = 'Update Order Address: ' . $model->id_order_address;
$this->params['breadcrumbs'][] = ['label' => 'Order Addresses', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_order_address, 'url' => ['view', 'id_order_address' => $model->id_order_address]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="order-address-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
