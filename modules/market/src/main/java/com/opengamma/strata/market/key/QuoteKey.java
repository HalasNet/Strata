/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.market.key;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.market.FieldName;
import com.opengamma.strata.basics.market.MarketDataFeed;
import com.opengamma.strata.basics.market.ObservableKey;
import com.opengamma.strata.collect.id.StandardId;
import com.opengamma.strata.market.id.QuoteId;

/**
 * Market data key identifying the current and historical values of a market identifier.
 * <p>
 * A quote key identifies a piece of data in an external data provider.
 * <p>
 * Quote keys should not normally to be used by calculation code. Higher level keys are
 * preferred, for example {@link IndexRateKey}. Higher level market data keys allow the system to
 * associate the market data with metadata when applying scenario definitions.
 * If quote keys are used directly, the system has no way to perturb the market data using
 * higher level rules that rely on metadata.
 * <p>
 * The {@link StandardId} in a quote key is typically the key from an underlying data provider (e.g.
 * Bloomberg or Reuters). However the field name is a generic name which is mapped to the field name
 * in the underlying provider by the market data system.
 * <p>
 * The reason for this difference is the different sources of the key and field name data. The key is typically
 * taken from an object which is provided to any calculations, for example a security linked to the
 * trade. The calculation rarely has to make up an key for an object it doesn't have access to.
 * <p>
 * In contrast, calculations will often have to reference field names that aren't part their arguments. For
 * example, if a calculation requires the last closing price of a security, it could take the key from
 * the security, but it needs a way to specify the field name containing the last closing price.
 * <p>
 * If the field name were specific to the market data provider, the calculation would have to be aware
 * of the source of its market data. However, if it uses a generic field name from {@code FieldNames}
 * the market data source can change without affecting the calculation.
 *
 * @see FieldName
 */
@BeanDefinition(builderScope = "private")
public final class QuoteKey
    implements ObservableKey, ImmutableBean, Serializable {

  /**
   * The ID of the market data that is required, typically an ID from an external data provider.
   * For example, 'Bloomberg~AAPL'.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final StandardId standardId;
  /**
   * The field name in the market data record that is required.
   * For example, {@link FieldName#MARKET_VALUE}.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final FieldName fieldName;

  //-------------------------------------------------------------------------
  /**
   * Creates a key to obtain the market value associated with an identifier.
   * <p>
   * This obtains the {@link FieldName#MARKET_VALUE MARKET_VALUE} field.
   *
   * @param id  the ID of the data in the underlying data provider
   * @return a key for the market quote of the identifier
   */
  public static QuoteKey of(StandardId id) {
    return new QuoteKey(id, FieldName.MARKET_VALUE);
  }

  /**
   * Creates a key to obtain a specific field associated with an identifier.
   * <p>
   * This obtains the specified {@linkplain FieldName field}.
   *
   * @param id  the ID of the data in the underlying data provider
   * @param fieldName  the field name in the market data record to obtain
   * @return a key for the market quote of the identifier
   */
  public static QuoteKey of(StandardId id, FieldName fieldName) {
    return new QuoteKey(id, fieldName);
  }

  //-------------------------------------------------------------------------
  @Override
  public QuoteId toObservableId(MarketDataFeed marketDataFeed) {
    return QuoteId.of(standardId, marketDataFeed, fieldName);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code QuoteKey}.
   * @return the meta-bean, not null
   */
  public static QuoteKey.Meta meta() {
    return QuoteKey.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(QuoteKey.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private QuoteKey(
      StandardId standardId,
      FieldName fieldName) {
    JodaBeanUtils.notNull(standardId, "standardId");
    JodaBeanUtils.notNull(fieldName, "fieldName");
    this.standardId = standardId;
    this.fieldName = fieldName;
  }

  @Override
  public QuoteKey.Meta metaBean() {
    return QuoteKey.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the ID of the market data that is required, typically an ID from an external data provider.
   * For example, 'Bloomberg~AAPL'.
   * @return the value of the property, not null
   */
  @Override
  public StandardId getStandardId() {
    return standardId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the field name in the market data record that is required.
   * For example, {@link FieldName#MARKET_VALUE}.
   * @return the value of the property, not null
   */
  @Override
  public FieldName getFieldName() {
    return fieldName;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      QuoteKey other = (QuoteKey) obj;
      return JodaBeanUtils.equal(getStandardId(), other.getStandardId()) &&
          JodaBeanUtils.equal(getFieldName(), other.getFieldName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getStandardId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFieldName());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("QuoteKey{");
    buf.append("standardId").append('=').append(getStandardId()).append(',').append(' ');
    buf.append("fieldName").append('=').append(JodaBeanUtils.toString(getFieldName()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code QuoteKey}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code standardId} property.
     */
    private final MetaProperty<StandardId> standardId = DirectMetaProperty.ofImmutable(
        this, "standardId", QuoteKey.class, StandardId.class);
    /**
     * The meta-property for the {@code fieldName} property.
     */
    private final MetaProperty<FieldName> fieldName = DirectMetaProperty.ofImmutable(
        this, "fieldName", QuoteKey.class, FieldName.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "standardId",
        "fieldName");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          return standardId;
        case 1265009317:  // fieldName
          return fieldName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends QuoteKey> builder() {
      return new QuoteKey.Builder();
    }

    @Override
    public Class<? extends QuoteKey> beanType() {
      return QuoteKey.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code standardId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> standardId() {
      return standardId;
    }

    /**
     * The meta-property for the {@code fieldName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FieldName> fieldName() {
      return fieldName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          return ((QuoteKey) bean).getStandardId();
        case 1265009317:  // fieldName
          return ((QuoteKey) bean).getFieldName();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code QuoteKey}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<QuoteKey> {

    private StandardId standardId;
    private FieldName fieldName;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          return standardId;
        case 1265009317:  // fieldName
          return fieldName;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1284477768:  // standardId
          this.standardId = (StandardId) newValue;
          break;
        case 1265009317:  // fieldName
          this.fieldName = (FieldName) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public QuoteKey build() {
      return new QuoteKey(
          standardId,
          fieldName);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("QuoteKey.Builder{");
      buf.append("standardId").append('=').append(JodaBeanUtils.toString(standardId)).append(',').append(' ');
      buf.append("fieldName").append('=').append(JodaBeanUtils.toString(fieldName));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}