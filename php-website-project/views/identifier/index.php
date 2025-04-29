<?php

use app\models\Identifier;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\IdentifierSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'Identifiers';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="identifier-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Identifier', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_identifier',
            'name',
            'id_product_type',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, Identifier $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id_identifier' => $model->id_identifier]);
                 }
            ],
        ],
    ]); ?>


</div>
