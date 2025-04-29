<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "identifier".
 *
 * @property int $id_identifier
 * @property string $name
 * @property int $id_product_type
 *
 * @property Meniu[] $menius
 * @property ProductType $productType
 */
class Identifier extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'identifier';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['name', 'id_product_type'], 'required'],
            [['id_product_type'], 'integer'],
            [['name'], 'string', 'max' => 50],
            [['id_product_type'], 'exist', 'skipOnError' => true, 'targetClass' => ProductType::class, 'targetAttribute' => ['id_product_type' => 'id_product_type']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_identifier' => 'Id Identifier',
            'name' => 'Name',
            'id_product_type' => 'Id Product Type',
        ];
    }

    /**
     * Gets query for [[Menius]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getMenius()
    {
        return $this->hasMany(Meniu::class, ['id_identifier' => 'id_identifier']);
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

}
