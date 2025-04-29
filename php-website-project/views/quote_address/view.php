<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var app\models\QuoteAddress $model */

$this->title = $model->id_quote_address;
$this->params['breadcrumbs'][] = ['label' => 'Quote Addresses', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
\yii\web\YiiAsset::register($this);
?>
<div class="quote-address-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id_quote_address' => $model->id_quote_address], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id_quote_address' => $model->id_quote_address], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'id_quote_address',
            'country',
            'county',
            'city',
            'street',
            'block',
            'entrance',
            'apartment',
            'type',
            'id_quote',
        ],
    ]) ?>

</div>
