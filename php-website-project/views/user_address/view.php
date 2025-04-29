<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var app\models\UserAddress $model */

$this->title = $model->id_user_address;
$this->params['breadcrumbs'][] = ['label' => 'User Addresses', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
\yii\web\YiiAsset::register($this);
?>
<div class="user-address-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id_user_address' => $model->id_user_address], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id_user_address' => $model->id_user_address], [
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
            'id_user_address',
            'country',
            'county',
            'city',
            'street',
            'block',
            'entrance',
            'apartment',
            'type',
            'id_user',
        ],
    ]) ?>

</div>
