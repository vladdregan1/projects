<?php

use app\models\Product;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\ProductSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = \Yii::t('app', 'dishes');
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="product-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a(\Yii::t('app', 'create'), ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            [
                'attribute' => 'id_product',
                'label' => 'ID ' . \Yii::t('app', 'dishes'),
            ],
            [
                'attribute' => 'product_name',
                'label' => \Yii::t('app', 'dishes'),
            ],
            [
                'attribute' => 'price',
                'value' => function ($model) {
                    return $model->price . ' Ron';
                },
            ],
            'description',
            [
                'attribute' => 'weight',
                'value' => function ($model) {
                    return $model->weight . ' g';
                },
            ],
            //'id_product_type',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, Product $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id_product' => $model->id_product]);
                 }
            ],
        ],
    ]); ?>


</div>
