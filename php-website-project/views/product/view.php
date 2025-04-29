<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var app\models\Product $model */

$this->title = $model->product_name;
$this->params['breadcrumbs'][] = ['label' => 'Products', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
\yii\web\YiiAsset::register($this);
?>
<div class="product-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id_product' => $model->id_product], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id_product' => $model->id_product], [
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
                [
                    'attribute' => 'id_product',
                    'label' => 'ID ' . \Yii::t('app', 'dish'),
                ],
                [
                    'attribute' => 'product_name',
                    'label' =>\Yii::t('app', 'name') . ' ' . \Yii::t('app', 'dish'),
                ],
            'price',
            'description',
            [
                'attribute' => 'weight',
                'label' =>\Yii::t('app', 'weight') . '(g)',
                'value' => function($model){
                    return $model->weight . ' g';
                }
            ],
            [
                'attribute' => 'id_product_type',
                'label' =>\Yii::t('app', 'type'),
                'value' => function($model){
                    return $model->productType->type;
                }
            ],
        ],
    ]) ?>

</div>
