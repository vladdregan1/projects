<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "product_type".
 *
 * @property int $id_product_type
 * @property string $type
 *
 * @property Identifier[] $identifiers
 * @property Product[] $products
 */
class ProductType extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'product_type';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['type'], 'required'],
            [['type'], 'string', 'max' => 50],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_product_type' => 'Id Product Type',
            'type' => 'Type',
        ];
    }

    /**
     * Gets query for [[Identifiers]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdentifiers()
    {
        return $this->hasMany(Identifier::class, ['id_product_type' => 'id_product_type']);
    }

    /**
     * Gets query for [[Products]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getProducts()
    {
        return $this->hasMany(Product::class, ['id_product_type' => 'id_product_type']);
    }

}
