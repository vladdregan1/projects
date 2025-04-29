<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\Meniu $model */
/** @var  $productItems */
/** @var  $identifierItems */

$this->title = \Yii::t('app', 'create menu');
$this->params['breadcrumbs'][] = ['label' => 'Menius', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="meniu-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
        'productItems' => $productItems,
        'identifierItems' => $identifierItems,
    ]) ?>

</div>
