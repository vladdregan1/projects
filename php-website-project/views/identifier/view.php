<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var app\models\Identifier $model */

$this->title = $model->name;
$this->params['breadcrumbs'][] = ['label' => 'Identifiers', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
\yii\web\YiiAsset::register($this);
?>
<div class="identifier-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id_identifier' => $model->id_identifier], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id_identifier' => $model->id_identifier], [
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
            'id_identifier',
            'name',
            'id_product_type',
        ],
    ]) ?>

</div>
