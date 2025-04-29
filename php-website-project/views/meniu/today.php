<?php

/** @var string $today */
/** @var app\models\Meniu[] $menuItems */

use yii\helpers\Html;

$this->title = \Yii::t('app', 'today menu') . " - $today";
$this->params['breadcrumbs'][] = $this->title;
?>

<div>
    <h1><?= Html::encode($this->title) ?></h1>

    <?php foreach ($menuItems as $categoryName => $products): ?>
        <div class="category-section">
            <h3><?= Html::encode($categoryName)?></h3>

            <div class="row">
                <?php foreach ($products as $product): ?>
                  <div class="product-box">
                      <div class="panel panel-default">
                          <div class="panel-heading">
                              <h5 class="panel-title">
                                  <?= Html::encode($product['product_name']) ?><br>
                              </h5>
                              <?= \Yii::t('app', 'price') ?>: <?= Html::encode($product['price']) ?> lei<br>
                              <?= \Yii::t('app', 'weight') ?>: <?= Html::encode($product['weight']) ?>g<br>
                              <?= \Yii::t('app', 'description'); ?>: <?= Html::encode($product['description']) ?>
                          </div>
                      </div>
                  </div>
                <?php endforeach; ?>
            </div>
        </div>
    <?php endforeach; ?>


</div>
