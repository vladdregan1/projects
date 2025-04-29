<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "product".
 *
 * @property int $id_product
 * @property string $product_name
 * @property float $price
 * @property string|null $description
 * @property int $weight
 * @property int $id_product_type
 *
 * @property Meniu[] $menius
 * @property ProductType $productType
 * @property Quote[] $quotes
 */
class Product extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'product';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['description'], 'default', 'value' => null],
            [['product_name', 'price', 'weight', 'id_product_type'], 'required'],
            [['price'], 'number'],
            [['weight', 'id_product_type'], 'integer'],
            [['product_name'], 'string', 'max' => 50],
            [['description'], 'string', 'max' => 100],
            [['id_product_type'], 'exist', 'skipOnError' => true, 'targetClass' => ProductType::class, 'targetAttribute' => ['id_product_type' => 'id_product_type']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_product' => 'Id Product',
            'product_name' => \Yii::t('app', 'product name'),
            'price' => \Yii::t('app', 'price'),
            'description' => \Yii::t('app', 'description'),
            'weight' => \Yii::t('app', 'weight'),
            'id_product_type' => \Yii::t('app', 'id product type'),
        ];
    }

    /**
     * Gets query for [[Menius]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMenius()
    {
        return $this->hasMany(Meniu::class, ['id_product' => 'id_product']);
    }

    /**
     * Gets query for [[ProductType]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getProductType()
    {
        return $this->hasOne(ProductType::class, ['id_product_type' => 'id_product_type']);
    }

    /**
     * Gets query for [[Quotes]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getQuotes()
    {
        return $this->hasMany(Quote::class, ['id_product' => 'id_product']);
    }

}
