<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\QuoteAddress $model */

$this->title = 'Update Quote Address: ' . $model->id_quote_address;
$this->params['breadcrumbs'][] = ['label' => 'Quote Addresses', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_quote_address, 'url' => ['view', 'id_quote_address' => $model->id_quote_address]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="quote-address-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
