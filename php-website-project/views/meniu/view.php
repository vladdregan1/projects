<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/** @var yii\web\View $this */
/** @var app\models\Meniu $model */

$this->title = $model->product->productType->type . ": " . $model->product->product_name;
$this->params['breadcrumbs'][] = ['label' => 'Menius', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
\yii\web\YiiAsset::register($this);
?>
<div class="meniu-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?php
        $currentDate = new \DateTime();
        $menuDate = new \DateTime($model->day);
        if ($menuDate > $currentDate) {
           echo Html::a('Update', ['update', 'id_meniu' => $model->id_meniu], ['class' => 'btn btn-primary']);
        }
        ?>
        <?= Html::a('Delete', ['delete', 'id_meniu' => $model->id_meniu], [
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
            'id_meniu',
            'day',
            'id_product',
            'id_identifier',
        ],
    ]) ?>

</div>
