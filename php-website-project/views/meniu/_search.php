<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/** @var yii\web\View $this */
/** @var app\models\MeniuSearch $model */
/** @var yii\widgets\ActiveForm $form */
?>

<div class="meniu-search">

    <?php $form = ActiveForm::begin([
        'action' => ['index'],
        'method' => 'get',
    ]); ?>

    <?= $form->field($model, 'id_meniu') ?>

    <?= $form->field($model, 'day') ?>

    <?= $form->field($model, 'id_product') ?>

    <?= $form->field($model, 'id_identifier') ?>

    <div class="form-group">
        <?= Html::submitButton('Search', ['class' => 'btn btn-primary']) ?>
        <?= Html::resetButton('Reset', ['class' => 'btn btn-outline-secondary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
