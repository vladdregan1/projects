<?php

use app\models\Meniu;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;
use kartik\date\DatePicker;

/** @var yii\web\View $this */
/** @var app\models\MeniuSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = \Yii::t('app', 'menus');
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="meniu-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a(\Yii::t('app', 'create menu'), ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            [
                'attribute' => 'day',
                'format' => ['date', 'php:d-m-Y'],
                'filter' => DatePicker::widget([
                    'model' => $searchModel,
                    'attribute' => 'day',
                    'options' => ['placeholder' => \Yii::t('app', 'selectDate')],
                    'pluginOptions' => [
                        'autoclose' => true,
                        'format' => 'yyyy-mm-dd',
                        'todayHighlight' => true,
                    ],
                ]),
            ],
            [
                'attribute' => 'product_name',
                'value' => 'product.product_name',
                'label' => \Yii::t('app', 'dishes'),
                'filter' => Html::activeDropDownList(
                    $searchModel,
                    'product_name',
                    \yii\helpers\ArrayHelper::map(
                        \app\models\Product::find()->select(['product_name'])->distinct()->orderBy('product_name')->all(),
                        'product_name',
                        'product_name'
                    ),
                    ['class' => 'form-control', 'prompt' => \Yii::t('app', 'allDishes')]
                ),
            ],
            [
                'attribute' => 'identifier_name',
                'value' => 'identifier.name',
                'label' => \Yii::t('app', 'identifier'),
                'filter' => Html::activeDropDownList(
                    $searchModel,
                    'identifier_name',
                    \yii\helpers\ArrayHelper::map(
                        \app\models\Identifier::find()->select(['name'])->distinct()->orderBy('name')->all(),
                        'name',
                        'name'
                    ),
                    ['class' => 'form-control', 'prompt' => \Yii::t('app', 'allIdentifiers')]
                ),
            ],
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, Meniu $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id_meniu' => $model->id_meniu]);
                 }
            ],
        ],
    ]); ?>


</div>
