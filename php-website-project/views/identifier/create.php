<?php

use yii\helpers\Html;

/** @var yii\web\View $this */
/** @var app\models\Identifier $model */
/** @var $identifierItems */

$this->title = 'Create Identifier';
$this->params['breadcrumbs'][] = ['label' => 'Identifiers', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="identifier-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
        'identifierItems' => $identifierItems,
    ]) ?>

</div>
