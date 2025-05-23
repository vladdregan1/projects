<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\UserAddress $model */

$this->title = 'Update User Address: ' . $model->id_user_address;
$this->params['breadcrumbs'][] = ['label' => 'User Addresses', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_user_address, 'url' => ['view', 'id_user_address' => $model->id_user_address]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="user-address-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
