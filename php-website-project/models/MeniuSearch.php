<?php

namespace app\models;

use yii\base\Model;
use yii\data\ActiveDataProvider;
use app\models\Meniu;

/**
 * MeniuSearch represents the model behind the search form of `app\models\Meniu`.
 */
class MeniuSearch extends Meniu
{
    /**
     * {@inheritdoc}
     */

    public $product_name;
    public $identifier_name;

    public function rules()
    {

        return [
            [['id_meniu', 'id_product', 'id_identifier'], 'integer'],
            [['day', 'product_name', 'identifier_name'], 'safe'],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function scenarios()
    {
        // bypass scenarios() implementation in the parent class
        return Model::scenarios();
    }

    /**
     * Creates data provider instance with search query applied
     *
     * @param array $params
     * @param string|null $formName Form name to be used into `->load()` method.
     *
     * @return ActiveDataProvider
     */
    public function search($params, $formName = null)
    {
        $query = Meniu::find();

        // add conditions that should always apply here

        $dataProvider = new ActiveDataProvider([
            'query' => $query,
        ]);

        $this->load($params, $formName);

        if (!$this->validate()) {
            // uncomment the following line if you do not want to return any records when validation fails
            // $query->where('0=1');
            return $dataProvider;
        }

        // grid filtering conditions
        $query->andFilterWhere([
            'id_meniu' => $this->id_meniu,
            'day' => $this->day,
            'id_product' => $this->id_product,
            'id_identifier' => $this->id_identifier,
        ]);

        if ($this->product_name){
            $query->joinWith(['product']);
        }
        if ($this->identifier_name){
            $query->joinWith(['identifier']);
        }
        $query->andFilterWhere(['like', 'product.product_name', $this->product_name]);
        $query->andFilterWhere(['like', 'identifier.name', $this->identifier_name]);

        return $dataProvider;
    }


}
