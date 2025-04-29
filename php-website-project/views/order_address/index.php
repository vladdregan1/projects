<?php

use app\models\OrderAddress;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\OrderAddressSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Order Addresses';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="order-address-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Order Address', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_order_address',
            'country',
            'county',
            'city',
            'street',
            //'block',
            //'entrance',
            //'apartment',
            //'type',
            //'id_order',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, OrderAddress $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id_order_address' => $model->id_order_address]);
                 }
            ],
        ],
    ]); ?>


</div>
