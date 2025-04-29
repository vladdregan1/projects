<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;

/** @var yii\web\View $this */
/** @var app\models\Product $model */
/** @var yii\widgets\ActiveForm $form */
/** @var array $productTypeItems */
?>

<div class="product-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'product_name')->label(\Yii::t('app', 'name') . ' ' . \Yii::t('app', 'dish')) ?>

    <?= $form->field($model, 'price')->textInput() ?>

    <?= $form->field($model, 'description')->textarea(['rows' => 4])?>

    <?= $form->field($model, 'weight')->label(\Yii::t('app', 'weight') . '(g)') ?>

    <?= $form->field($model, 'id_product_type')->dropDownList($productTypeItems)->label('ID ' . \Yii::t('app', 'type')) ?>

    <div class="form-group">
        <?= Html::submitButton(\Yii::t('app', 'save'), ['class' => 'btn btn-success']) ?>
        <?= Html::a(\Yii::t('app', 'back'), ['index'], ['class' => 'btn btn-secondary']) ?>

    </div>

    <?php ActiveForm::end(); ?>

</div>
