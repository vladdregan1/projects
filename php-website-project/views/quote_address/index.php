<?php

use app\models\QuoteAddress;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\QuoteAddressSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Quote Addresses';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="quote-address-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Quote Address', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_quote_address',
            'country',
            'county',
            'city',
            'street',
            //'block',
            //'entrance',
            //'apartment',
            //'type',
            //'id_quote',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, QuoteAddress $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id_quote_address' => $model->id_quote_address]);
                 }
            ],
        ],
    ]); ?>


</div>
