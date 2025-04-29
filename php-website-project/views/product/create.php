<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\Product $model */
/** @var $productTypeItems */

$this->title = \Yii::t('app', 'create') . ' ' . \Yii::t('app', 'dish');
$this->params['breadcrumbs'][] = ['label' => 'Products', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="product-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
        'productTypeItems' => $productTypeItems,
    ]) ?>

</div>
