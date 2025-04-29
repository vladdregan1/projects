<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\Identifier $model */
/** @var $identifierItems */

$this->title = 'Update Identifier: ' . $model->name;
$this->params['breadcrumbs'][] = ['label' => 'Identifiers', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->name, 'url' => ['view', 'id_identifier' => $model->id_identifier]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="identifier-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
        'identifierItems' => $identifierItems,
    ]) ?>

</div>
