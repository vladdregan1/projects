<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "payment".
 *
 * @property int $id_payment
 * @property string $type
 * @property string $status
 * @property int $id_order
 *
 * @property Order $order
 */
class Payment extends \yii\db\ActiveRecord
{


    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'payment';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['type', 'status', 'id_order'], 'required'],
            [['id_order'], 'integer'],
            [['type', 'status'], 'string', 'max' => 50],
            [['id_order'], 'exist', 'skipOnError' => true, 'targetClass' => Order::class, 'targetAttribute' => ['id_order' => 'id_order']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id_payment' => 'Id Payment',
            'type' => 'Type',
            'status' => 'Status',
            'id_order' => 'Id Order',
        ];
    }

    /**
     * Gets query for [[Order]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getOrder()
    {
        return $this->hasOne(Order::class, ['id_order' => 'id_order']);
    }

}
