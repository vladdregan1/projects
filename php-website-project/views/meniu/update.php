<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\Meniu $model */
/** @var $productItems */
/** @var $identifierItems */

$this->title = 'Update Meniu: ' . $model->id_meniu;
$this->params['breadcrumbs'][] = ['label' => 'Menius', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->id_meniu, 'url' => ['view', 'id_meniu' => $model->id_meniu]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="meniu-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
        'productItems' => $productItems,
        'identifierItems' => $identifierItems,
    ]) ?>

</div>
