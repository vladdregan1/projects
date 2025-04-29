<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use kartik\datetime\DateTimePicker;

/** @var yii\web\View $this */
/** @var app\models\Meniu $model */
/** @var yii\widgets\ActiveForm $form */
/** @var array $productItems */
/** @var array $identifierItems */
?>

<div class="meniu-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'day')->widget(\kartik\date\DatePicker::class, [
            'options' => ['placeholder' => \Yii::t('app', 'select date')],
            'pluginOptions' => [
            'autoclose' => true,
            'format' => 'dd.mm.yyyy',
            'todayHighlight' => true,
            'startDate' => date('d.m.Y'),
            ]
    ])?>

    <?= $form->field($model, 'id_product')->dropDownList($productItems) ?>

    <?= $form->field($model, 'id_identifier')->dropDownList($identifierItems) ?>

    <div class="form-group">
        <?= Html::submitButton(\Yii::t('app', 'save'), ['class' => 'btn btn-success']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
