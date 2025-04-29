<?php

use app\models\UserAddress;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\grid\ActionColumn;
use yii\grid\GridView;

/** @var yii\web\View $this */
/** @var app\models\UserAddressSearch $searchModel */
/** @var yii\data\ActiveDataProvider $dataProvider */

$this->title = 'User Addresses';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="user-address-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create User Address', ['create'], ['class' => 'btn btn-success']) ?>
    </p>

    <?php // echo $this->render('_search', ['model' => $searchModel]); ?>

    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'filterModel' => $searchModel,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id_user_address',
            'country',
            'county',
            'city',
            'street',
            //'block',
            //'entrance',
            //'apartment',
            //'type',
            //'id_user',
            [
                'class' => ActionColumn::className(),
                'urlCreator' => function ($action, UserAddress $model, $key, $index, $column) {
                    return Url::toRoute([$action, 'id_user_address' => $model->id_user_address]);
                 }
            ],
        ],
    ]); ?>


</div>
