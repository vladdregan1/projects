<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\OrderStatus $model */

$this->title = 'Update Order Status: ' . $model->id_status;
$this->params['breadcrumbs'][] = ['label' => 'Order Statuses', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_status, 'url' => ['view', 'id_status' => $model->id_status]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="order-status-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
