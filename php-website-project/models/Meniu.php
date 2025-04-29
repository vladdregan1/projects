<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "meniu".
 *
 * @property int $id_meniu
 * @property string $day
 * @property int $id_product
 * @property int $id_identifier
 *
 * @property Identifier $identifier
 * @property Product $product
 */
class Meniu extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'meniu';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['day', 'id_product', 'id_identifier'], 'required'],
            [['day'], 'safe'],
            [['id_product', 'id_identifier'], 'integer'],
            [['id_identifier'], 'exist', 'skipOnError' => true, 'targetClass' => Identifier::class, 'targetAttribute' => ['id_identifier' => 'id_identifier']],
            [['id_product'], 'exist', 'skipOnError' => true, 'targetClass' => Product::class, 'targetAttribute' => ['id_product' => 'id_product']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_meniu' => 'Id Meniu',
            'day' => \Yii::t('app', 'day'),
            'id_product' => \Yii::t('app', 'products'),
            'id_identifier' => \Yii::t('app', 'identifier'),
        ];
    }

    /**
     * Gets query for [[Identifier]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getIdentifier()
    {
        return $this->hasOne(Identifier::class, ['id_identifier' => 'id_identifier']);
    }

    /**
     * Gets query for [[Product]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getProduct()
    {
        return $this->hasOne(Product::class, ['id_product' => 'id_product']);
    }

    public function beforeSave($insert)
    {
        $this->day = (new \DateTime($this->day))->format('Y-m-d');

        return parent::beforeSave($insert);
    }

}
